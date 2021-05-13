/*
 * Created by JFormDesigner on Thu May 13 22:05:27 CEST 2021
 */

package dev.thcfree.ShopBot;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Utils.ConfigUtils;
import net.miginfocom.swing.*;

/**
 * @author THCFree
 */
public class UserUI extends JPanel {
    public UserUI() {
        initComponents();
        TokenField.setText(Constants.Token);
        GuildIDField.setText(Constants.GUID);
        ActivityField.setText(Constants.Activity);
        OwnerIdField.setText(Constants.OwnerID);
        ShopRoleField.setText(Constants.ShopRole);
        CurrencyField.setText(Constants.Currency);
        PrefixField.setText(Constants.Prefix);
        MinPurchaseField.setText(String.valueOf(Constants.MinPurchase));
        MentionRoleCheckBox.setSelected(Constants.MentionRole);
        StatusLabel.setText(Constants.Running ? "<html>Status: <font color='green'>Running</font></html>" : "<html>Status: <font color='red'>Not Running</font></html>");
    }

    private void SaveConfigButtonActionPerformed(ActionEvent e) {
        try {
            Constants.Token = TokenField.getText();
            Constants.GUID = GuildIDField.getText();
            Constants.Activity = ActivityField.getText();
            Constants.OwnerID = OwnerIdField.getText();
            Constants.ShopRole = ShopRoleField.getText();
            Constants.Currency = CurrencyField.getText();
            Constants.Prefix = PrefixField.getText();
            Constants.MinPurchase = Double.valueOf(MinPurchaseField.getText());
            Constants.MentionRole = MentionRoleCheckBox.isSelected();
            ConfigUtils.saveConfig();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void StopButtonActionPerformed(ActionEvent e) {
        Main.stopBot();
        StatusLabel.setText("<html>Status: <font color='red'>Not Running</font></html>");
    }

    private void StartButtonActionPerformed(ActionEvent e) {
        Main.startBot();
        StatusLabel.setText("<html>Status: <font color='green'>Running</font></html>");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        Title = new JLabel();
        StatusLabel = new JLabel();
        TokenLabel = new JLabel();
        TokenField = new JTextField();
        GuildIdLabel = new JLabel();
        GuildIDField = new JTextField();
        ActivityLabel = new JLabel();
        ActivityField = new JTextField();
        OwnerIdLabel = new JLabel();
        OwnerIdField = new JTextField();
        ShopRoleLabel = new JLabel();
        ShopRoleField = new JTextField();
        CurrencyLabel = new JLabel();
        CurrencyField = new JTextField();
        PrefixLabel = new JLabel();
        PrefixField = new JTextField();
        MinPurchaseLabel = new JLabel();
        MinPurchaseField = new JTextField();
        MentionRoleCheckBox = new JCheckBox();
        SaveConfigButton = new JButton();
        StartButton = new JButton();
        StopButton = new JButton();

        //======== this ========
        setLayout(new MigLayout(
                "fillx,hidemode 3,align center top",
                // columns
                "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //---- Title ----
        Title.setText("Discord Shop Bot");
        add(Title, "cell 0 0,alignx center,growx 0");

        //---- StatusLabel ----
        StatusLabel.setText("Status:");
        add(StatusLabel, "cell 0 1,alignx center,growx 0");

        //---- TokenLabel ----
        TokenLabel.setText("Token:");
        add(TokenLabel, "cell 0 2,alignx right,growx 0");
        add(TokenField, "cell 0 2");

        //---- GuildIdLabel ----
        GuildIdLabel.setText("Guild ID:");
        add(GuildIdLabel, "cell 0 3,alignx right,growx 0");
        add(GuildIDField, "cell 0 3");

        //---- ActivityLabel ----
        ActivityLabel.setText("Activity:");
        add(ActivityLabel, "cell 0 4,alignx right,growx 0");
        add(ActivityField, "cell 0 4");

        //---- OwnerIdLabel ----
        OwnerIdLabel.setText("Owner ID:");
        add(OwnerIdLabel, "cell 0 5,alignx right,growx 0");
        add(OwnerIdField, "cell 0 5");

        //---- ShopRoleLabel ----
        ShopRoleLabel.setText("Shop Role:");
        add(ShopRoleLabel, "cell 0 6,alignx right,growx 0");
        add(ShopRoleField, "cell 0 6");

        //---- CurrencyLabel ----
        CurrencyLabel.setText("Currency:");
        add(CurrencyLabel, "cell 0 7,alignx right,growx 0");
        add(CurrencyField, "cell 0 7,alignx left,growx 0");

        //---- PrefixLabel ----
        PrefixLabel.setText("Prefix:");
        add(PrefixLabel, "cell 0 7,alignx right,growx 0");
        add(PrefixField, "cell 0 7,alignx left,growx 0");

        //---- MinPurchaseLabel ----
        MinPurchaseLabel.setText("Min Purchase:");
        add(MinPurchaseLabel, "cell 0 7,alignx right,growx 0");
        add(MinPurchaseField, "cell 0 7,alignx left,growx 0");

        //---- MentionRoleCheckBox ----
        MentionRoleCheckBox.setText("Mention Role");
        add(MentionRoleCheckBox, "cell 0 7,alignx right,growx 0");

        //---- SaveConfigButton ----
        SaveConfigButton.setText("Save Config");
        SaveConfigButton.addActionListener(e -> SaveConfigButtonActionPerformed(e));
        add(SaveConfigButton, "cell 0 8");

        //---- StartButton ----
        StartButton.setText("Start Bot");
        StartButton.addActionListener(e -> StartButtonActionPerformed(e));
        add(StartButton, "cell 0 8");

        //---- StopButton ----
        StopButton.setText("Stop Bot");
        StopButton.addActionListener(e -> StopButtonActionPerformed(e));
        add(StopButton, "cell 0 9");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel Title;
    private JLabel StatusLabel;
    private JLabel TokenLabel;
    private JTextField TokenField;
    private JLabel GuildIdLabel;
    private JTextField GuildIDField;
    private JLabel ActivityLabel;
    private JTextField ActivityField;
    private JLabel OwnerIdLabel;
    private JTextField OwnerIdField;
    private JLabel ShopRoleLabel;
    private JTextField ShopRoleField;
    private JLabel CurrencyLabel;
    private JTextField CurrencyField;
    private JLabel PrefixLabel;
    private JTextField PrefixField;
    private JLabel MinPurchaseLabel;
    private JTextField MinPurchaseField;
    private JCheckBox MentionRoleCheckBox;
    private JButton SaveConfigButton;
    private JButton StartButton;
    private JButton StopButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
