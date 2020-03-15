package com.gmail.kirill.ui.views;

import com.gmail.kirill.backend.BankAccount;
import com.gmail.kirill.backend.DummyData;
import com.gmail.kirill.backend.entity.Company;
import com.gmail.kirill.backend.repository.CompanyRepository;
import com.gmail.kirill.ui.MainLayout;
import com.gmail.kirill.ui.components.FlexBoxLayout;
import com.gmail.kirill.ui.components.ListItem;
import com.gmail.kirill.ui.components.navigation.bar.AppBar;
import com.gmail.kirill.ui.layout.size.Bottom;
import com.gmail.kirill.ui.layout.size.Horizontal;
import com.gmail.kirill.ui.layout.size.Top;
import com.gmail.kirill.ui.layout.size.Vertical;
import com.gmail.kirill.ui.util.BoxShadowBorders;
import com.gmail.kirill.ui.util.LumoStyles;
import com.gmail.kirill.ui.util.TextColor;
import com.gmail.kirill.ui.util.UIUtils;
import com.gmail.kirill.ui.util.css.BorderRadius;
import com.gmail.kirill.ui.util.css.FlexDirection;
import com.gmail.kirill.ui.util.css.FlexWrap;
import com.gmail.kirill.ui.util.css.WhiteSpace;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@PageTitle("Company Details")
@Route(value = "company-details", layout = MainLayout.class)
public class CompanyDetails extends ViewFrame implements HasUrlParameter<Long> {

    public int RECENT_TRANSACTIONS = 4;

    private ListItem name;
    private ListItem short_name;

//    private ListItem availability;
//    private ListItem bankAccount;
//    private ListItem updated;

    private Company company;
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyDetails(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        setViewContent(createContent());
        company = companyRepository.findById(id).orElse(null);
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(
                createLogoSection(),
                createRecentTransactionsHeader(),
                createRecentTransactionsList(),
                createMonthlyOverviewHeader(),
                createMonthlyOverviewChart()
        );
        content.setFlexDirection(FlexDirection.COLUMN);
        content.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
        content.setMaxWidth("840px");
        return content;
    }

    private FlexBoxLayout createLogoSection() {
        Image image = DummyData.getLogo();
        image.addClassName(LumoStyles.Margin.Horizontal.L);
        UIUtils.setBorderRadius(BorderRadius._50, image);
        image.setHeight("200px");
        image.setWidth("200px");


        name = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.FILE), "", "Name");
        name.getPrimary().addClassName(LumoStyles.Heading.H2);
        name.setDividerVisible(true);
        name.setId("name");
        name.setReverse(true);

        short_name = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.FILE_CODE), "", "Short name");
        short_name.getPrimary().addClassName(LumoStyles.Heading.H2);
        short_name.setDividerVisible(true);
        short_name.setId("short_name");
        short_name.setReverse(true);

//        availability = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "", "Availability");
//        availability.getPrimary().addClassName(LumoStyles.Heading.H2);
//        availability.setDividerVisible(true);
//        availability.setId("availability");
//        availability.setReverse(true);
//
//        bankAccount = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.INSTITUTION), "", "");
//        bankAccount.setDividerVisible(true);
//        bankAccount.setId("bankAccount");
//        bankAccount.setReverse(true);
//        bankAccount.setWhiteSpace(WhiteSpace.PRE_LINE);
//
//        updated = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), "", "Updated");
//        updated.setReverse(true);

//        FlexBoxLayout listItems = new FlexBoxLayout(availability, bankAccount, updated);
        FlexBoxLayout listItems = new FlexBoxLayout(name, short_name);
        listItems.setFlexDirection(FlexDirection.COLUMN);

        FlexBoxLayout section = new FlexBoxLayout(image, listItems);
        section.addClassName(BoxShadowBorders.BOTTOM);
        section.setAlignItems(FlexComponent.Alignment.CENTER);
        section.setFlex("1", listItems);
        section.setFlexWrap(FlexWrap.WRAP);
        section.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        section.setPadding(Bottom.L);
        return section;
    }

    private Component createRecentTransactionsHeader() {
        Label title = UIUtils.createH3Label("Recent Transactions");

        Button viewAll = UIUtils.createSmallButton("View All");
        viewAll.addClickListener(
                e -> UIUtils.showNotification("Not implemented yet."));
        viewAll.addClassName(LumoStyles.Margin.Left.AUTO);

        FlexBoxLayout header = new FlexBoxLayout(title, viewAll);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setMargin(Bottom.M, Horizontal.RESPONSIVE_L, Top.L);
        return header;
    }

    private Component createRecentTransactionsList() {
        Div items = new Div();
//        items.addClassNames(BoxShadowBorders.BOTTOM, LumoStyles.Padding.Bottom.L);
//
//        for (int i = 0; i < RECENT_TRANSACTIONS; i++) {
//            Double amount = DummyData.getAmount();
//            Label amountLabel = UIUtils.createAmountLabel(amount);
//            if (amount > 0) {
//                UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
//            } else {
//                UIUtils.setTextColor(TextColor.ERROR, amountLabel);
//            }
//            ListItem item = new ListItem(
//                    DummyData.getLogo(),
//                    DummyData.getCompany(),
//                    UIUtils.formatDate(LocalDate.now().minusDays(i)),
//                    amountLabel
//            );
//            // Dividers for all but the last item
//            item.setDividerVisible(i < RECENT_TRANSACTIONS - 1);
//            items.add(item);
//        }

        return items;
    }

    private Component createMonthlyOverviewHeader() {
        Label header = UIUtils.createH3Label("Monthly Overview");
        header.addClassNames(LumoStyles.Margin.Vertical.L, LumoStyles.Margin.Responsive.Horizontal.L);
        return header;
    }

    private Component createMonthlyOverviewChart() {
        Chart chart = new Chart(ChartType.COLUMN);
//
//        Configuration conf = chart.getConfiguration();
//        conf.setTitle("");
//        conf.getLegend().setEnabled(true);
//
//        XAxis xAxis = new XAxis();
//        xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
//        conf.addxAxis(xAxis);
//
//        conf.getyAxis().setTitle("Amount ($)");
//
//        // Withdrawals and deposits
//        ListSeries withDrawals = new ListSeries("Withdrawals");
//        ListSeries deposits = new ListSeries("Deposits");
//
//        for (int i = 0; i < 8; i++) {
//            withDrawals.addData(DummyData.getRandomInt(5000, 10000));
//            deposits.addData(DummyData.getRandomInt(5000, 10000));
//        }
//
//        conf.addSeries(withDrawals);
//        conf.addSeries(deposits);
//
        FlexBoxLayout card = new FlexBoxLayout(chart);
        card.setHeight("400px");
        return card;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        initAppBar();
//        UI.getCurrent().getPage().setTitle(account.getOwner());
        UI.getCurrent().getPage().setTitle(company.getName());

        name.setPrimaryText(company.getName());
        short_name.setPrimaryText(company.getShortName());
//        availability.setPrimaryText(UIUtils.formatAmount(account.getAvailability()));
//        bankAccount.setPrimaryText(account.getAccount());
//        bankAccount.setSecondaryText(account.getBank());
//        updated.setPrimaryText(UIUtils.formatDate(account.getUpdated()));
    }

    private AppBar initAppBar() {
        AppBar appBar = MainLayout.get().getAppBar();
        appBar.setNaviMode(AppBar.NaviMode.CONTEXTUAL);
        appBar.getContextIcon().addClickListener(e -> UI.getCurrent().navigate(Companies.class));
        appBar.setTitle(company.getName());
        return appBar;
    }
}