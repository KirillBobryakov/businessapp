package com.gmail.kirill.ui.components.detailsdrawer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.tabs.Tabs;
import com.gmail.kirill.ui.components.FlexBoxLayout;
import com.gmail.kirill.ui.layout.size.Horizontal;
import com.gmail.kirill.ui.layout.size.Right;
import com.gmail.kirill.ui.layout.size.Vertical;
import com.gmail.kirill.ui.util.BoxShadowBorders;
import com.gmail.kirill.ui.util.UIUtils;
import com.gmail.kirill.ui.util.css.FlexDirection;

public class DetailsDrawerHeader extends FlexBoxLayout {

    private Button close;
    private Label title;

    public DetailsDrawerHeader(String title) {
        addClassName(BoxShadowBorders.BOTTOM);
        setFlexDirection(FlexDirection.COLUMN);
        setWidthFull();

        this.close = UIUtils.createTertiaryInlineButton(VaadinIcon.CLOSE);
        UIUtils.setLineHeight("1", this.close);

        this.title = UIUtils.createH4Label(title);

        FlexBoxLayout wrapper = new FlexBoxLayout(this.close, this.title);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setPadding(Horizontal.RESPONSIVE_L, Vertical.M);
        wrapper.setSpacing(Right.L);
        add(wrapper);
    }

    public DetailsDrawerHeader(String title, Tabs tabs) {
        this(title);
        add(tabs);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void addCloseListener(ComponentEventListener<ClickEvent<Button>> listener) {
        this.close.addClickListener(listener);
    }

}