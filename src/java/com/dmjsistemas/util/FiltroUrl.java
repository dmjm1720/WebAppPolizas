package com.dmjsistemas.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class FiltroUrl implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String paginaActual = facesContext.getViewRoot().getViewId();

        if ("/index.xhtml".equals(paginaActual)) {
            return;
        }

        boolean isPageLogin = paginaActual.lastIndexOf("/index.xhtml") > -1 ? true : true;
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object nombre = session.getAttribute("nombre");
        if (!isPageLogin && nombre == null) {
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/index.xhtml");

        } 
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
