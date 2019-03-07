package com.insoft.practice.components;

import com.insoft.practice.bl.services.ImageService;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;

public class Components extends HtmlMacroComponent {
    private static final long serialVersionUID = 7935967626121317003L;

    @WireVariable
    private ImageService imageService;

    @Wire
    private Button delete_button;

    public Components () {
        compose();
    }

    public void doDeleteImage(){
        Events.postEvent(new Event("onClick", this));
    }

}
