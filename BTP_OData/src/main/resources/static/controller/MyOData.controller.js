sap.ui.define(
		["sap/ui/core/mvc/Controller",
		 "jquery.sap.global",
		 "anubhav/util/service",
		 "sap/m/MessageBox",
		 "sap/m/Dialog",
		 "sap/m/Button",
		 "sap/m/ButtonType",
		 "sap/m/MessageToast"],
		function(Controller, jQuery, service, MessageBox, Dialog, Button, ButtonType, MessageToast){
	return Controller.extend("anubhav.controller.Main",{
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
			//Set the model object to the Entire App level
			this.getView().setModel(oModel, "local");
		},
		oDefaultDialog: null,
		onAdd: function(){
			
			//Step 1: Create a new popup
			if (!this.oDefaultDialog) {
				this.oDefaultDialog = new Dialog({
					title: "Create Vendor",
					content: new sap.ui.layout.form.SimpleForm({
						content: [
							new sap.m.Label({text: "Company Name"}),
							new sap.m.Input({value: "{/vendor/CompanyName}"}),
							new sap.m.Label({text: "First Name"}),
							new sap.m.Input({value: "{/vendor/FirstName}"}),
							new sap.m.Label({text: "Website"}),
							new sap.m.Input({value: "{/vendor/Website}"}),
							new sap.m.Label({text: "Street"}),
							new sap.m.Input({value: "{/vendor/Addresses/0/Street}"}),
							new sap.m.Label({text: "City"}),
							new sap.m.Input({value: "{/vendor/Addresses/0/City}"}),
							new sap.m.Label({text: "Country"}),
							new sap.m.Input({value: "{/vendor/Addresses/0/Country}"})
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

				// to get access to the controller's model
				this.oDefaultDialog.setModel(this.getView().getModel("local"));
				this.getView().addDependent(this.oDefaultDialog);
				
			}

			this.oDefaultDialog.open();
			//Step 2: Bind data to popup
			//Step 3: make popup dependent on view
			//Step 4: Open popup
			//Step 5: on save trigger the save

			
		},
		onSave: function(){
			var that = this;
			//Step 1: get the odata model object
			var oModel = this.getView().getModel();
			//Step 2: Prepare payload
			var payload = this.getView().getModel("local").getProperty("/vendor");
			payload.Email = payload.FirstName + "@" + payload.Website;
			payload.LastName = "";
			//Step 3: Save Data
			oModel.create("/VendorSet", payload,{
				success: function(){
					MessageToast.show("Vendor has been created Successfully");
					that.oDefaultDialog.close();
				}
			});
		},
		onLoadData: function(){
			//alert("todo: we will call our microservice to load vendors");
			var that = this;
			service.callService("/vendor", "GET", {}).then(function(data){
				//console.log(data);
				//Get the object of the table
				var oTable = that.getView().byId("idTable");
				//Create a new ui5 model which contains data
				var oModel = sap.ui.getCore().getModel();
				oModel.setProperty("/vendor",data._embedded.vendor);
				//Set the model object to the Entire App level
				//Bind aggregation of the table
				oTable.bindRows("/vendor");
			}).catch(function(oError){
				
			});
		}
	});
});