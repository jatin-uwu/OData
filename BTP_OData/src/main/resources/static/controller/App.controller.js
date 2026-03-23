sap.ui.define(
    ["sap/ui/core/mvc/Controller"],
    function(Controller){
    return Controller.extend("anubhav.controller.App", {
        onInit: function(){
            var oDataModel = new sap.ui.model.odata.v2.ODataModel("/jatin.svc");
            this.getView().setModel(oDataModel);
        }
    });
});