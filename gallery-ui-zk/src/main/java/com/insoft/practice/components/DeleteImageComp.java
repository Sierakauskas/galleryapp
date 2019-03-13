package com.insoft.practice.components;

import com.insoft.practice.bl.services.ImageService;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;

public class DeleteImageComp extends HtmlMacroComponent {
    private static final long serialVersionUID = 7935967626121317003L;

    @WireVariable
    private ImageService imageService;

    private Long id;

    public DeleteImageComp() {

    }

    @Init
    public void init(@ExecutionArgParam("label") Long id) {
        this.id = id;
    }



    @Listen("onClick=#label")
    public void doDeleteImage() {
        imageService.deleteImage(id);
        Executions.getCurrent().sendRedirect("/zul/delete.zul?id=" + id);
    }

//    protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
//            throws java.io.IOException {
//        super.renderProperties(renderer);
//        render(renderer, "value", someWord);
    //        Events.postEvent(new Event("onClick", this));
//        smartUpdate("value", someWord);
//    }
}

//        Events.postEvent(new Event("onClick", this, null));
//        Executions.createComponents("/WEB-INF/component/deleteImage.zul", this, null);
//        compose();
