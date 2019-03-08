package com.insoft.practice.components;

import com.insoft.practice.bl.services.ImageService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;


public class Components extends HtmlMacroComponent {
    private static final long serialVersionUID = 7935967626121317003L;

    @WireVariable
    private ImageService imageService;

    @Wire
    private Button delete_button;

    private String someWord;

    public Components () {
//        Executions.createComponents("/zul/component/component.zul", this, null);
        setMacroURI("/zul/component/component.zul");
        compose();
    }

    public void doDeleteImage() {
        Events.postEvent(new Event("onClick", this));
        smartUpdate("value", someWord);
    }

    protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
            throws java.io.IOException {
        super.renderProperties(renderer);
        render(renderer, "value", someWord);
    }
}
