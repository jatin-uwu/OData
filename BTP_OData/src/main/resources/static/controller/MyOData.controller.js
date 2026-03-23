sap.ui.define(
    ["sap/ui/core/mvc/Controller",
     "sap/m/MessageBox",
     "sap/m/Dialog",
     "sap/m/Button",
     "sap/m/ButtonType",
     "sap/m/MessageToast"],
    function(Controller, MessageBox, Dialog, Button, ButtonType, MessageToast){
    return Controller.extend("anubhav.controller.MyOData", {  // ← fixed name
        onInit: function(){
            var oModel = new sap.ui.model.json.JSONModel();
            oModel.setData({"vendor": {
                "CompanyName": "IBM",
                "FirstName": "Veronica",
                "LastName": "Victoria",
                "Website": "ibm.com",
                "Email": "veronica@sap.com",
                "Status": "A",
                "GstNo": "GSTIN465465",
                "Addresses":[{
                    "AddressType": "H",
                    "Street": "MG Road",
                    "City": "Gurgaon",
                    "Country": "India",
                    "Region": "APJ"
                }]
            }});
            this.getView().setModel(oModel, "local");
        },
        oDefaultDialog: null,
        onAdd: function(){
            if (!this.oDefaultDialog) {
                this.oDefaultDialog = new Dialog({
                    title: "Create Vendor",
                    content: new sap.ui.layout.form.SimpleForm({
                        content: [
                            new sap.m.Label({text: "Company Name"}),
                            new sap.m.Input({value: "{local>/vendor/CompanyName}"}),
                            new sap.m.Label({text: "First Name"}),
                            new sap.m.Input({value: "{local>/vendor/FirstName}"}),
                            new sap.m.Label({text: "Website"}),
                            new sap.m.Input({value: "{local>/vendor/Website}"}),
                            new sap.m.Label({text: "Street"}),
                            new sap.m.Input({value: "{local>/vendor/Addresses/0/Street}"}),
                            new sap.m.Label({text: "City"}),
                            new sap.m.Input({value: "{local>/vendor/Addresses/0/City}"}),
                            new sap.m.Label({text: "Country"}),
                            new sap.m.Input({value: "{local>/vendor/Addresses/0/Country}"})
                        ]
                    }),
                    beginButton: new Button({
                        type: ButtonType.Emphasized,
                        text: "Save",
                        press: this.onSave.bind(this)
                    }),
                    endButton: new Button({
                        text: "Close",
                        press: function () {
                            this.oDefaultDialog.close();
                        }.bind(this)
                    })
                });
                this.oDefaultDialog.setModel(this.getView().getModel("local"), "local");
                this.getView().addDependent(this.oDefaultDialog);
            }
            this.oDefaultDialog.open();
        },
        onSave: function(){
            var that = this;
            var oModel = this.getView().getModel();
            var payload = this.getView().getModel("local").getProperty("/vendor");
            payload.Email = payload.FirstName + "@" + payload.Website;
            payload.LastName = "";
            oModel.create("/VendorSet", payload, {
                success: function(){
                    MessageToast.show("Vendor has been created Successfully");
                    that.oDefaultDialog.close();
                },
                error: function(oError){
                    MessageToast.show("Error: " + oError.message);
                }
            });
        }
    });
});