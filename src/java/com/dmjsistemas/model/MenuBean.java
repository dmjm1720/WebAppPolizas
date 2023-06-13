package com.dmjsistemas.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("menu")
@ViewScoped
public class MenuBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MenuModel model;

    public MenuBean() {

    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        if (us.getPerfil().equals("Administrador")) {
            // First submenu
            DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("MENÚ").build();

            DefaultMenuItem item = DefaultMenuItem
                    .builder()
                    .value("Polizas")
                    .icon("pi pi-cog")
                    .outcome("/views/Polizas")
                    .build();
            firstSubmenu.getElements().add(item);
            DefaultMenuItem item2 = DefaultMenuItem
                    .builder()
                    .value("Reporte")
                    .icon("pi pi-cog")
                    .outcome("/views/Reporte")
                    .build();
            firstSubmenu.getElements().add(item2);

            model.getElements().add(firstSubmenu);
        } 

    }

    public MenuModel getModel() {
        return model;
    }

}
