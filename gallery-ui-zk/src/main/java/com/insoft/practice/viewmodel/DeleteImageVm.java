package com.insoft.practice.viewmodel;

import lombok.Getter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;

import java.io.Serializable;

public class DeleteImageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @Getter
    private String message;

    @Init
    @NotifyChange("message")
    public void init(@QueryParam("id") Long id) {
        message = "Image #" + id + " succesfully deleted";
    }
}