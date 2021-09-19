package ClientSide;



public class Client {
    private static ClientCommunicate ClientCommunicate =null;
    private static Display  display=null;
    private static DataInput dataInput=null;
    public static void main(String[] args) {
        display=new Display();
        dataInput=new DataInput();
        ClientCommunicate =new ClientCommunicate();
        ClientCommunicate.init();
        if(isClient()){
           display.ShowHelloMessage();
           SelectOperation();
        }
        else {
           display.ShowSorryMessage();
           ClientCommunicate.CommunicateInOrderStopServer();
        }
        ClientCommunicate.close();
    }
    public static Boolean isClient(){
        return ClientCommunicate.CommunicateInOrderLogin();
    }
    public static void SelectOperation(){
        Boolean isFound=true;
        while (isFound){
            display.ShowMenu();
            int OperationNumber=dataInput.getOperationNumber();
            switch (OperationNumber){
                case 1:
                    ClientCommunicate.CommunicateInOrderInsert();break;
                case 2:
                    ClientCommunicate.CommunicateInOrderDelete();break;
                case 3:
                    ClientCommunicate.CommunicateInOrderUpdate();break;
                case 4:
                    ClientCommunicate.CommunicateInOrderSearch();break;
                case 5:
                    ClientCommunicate.CommunicateInOrderCreateUser();break;
                default:
                    ClientCommunicate.CommunicateInOrderLogout();isFound=false;
            }
        }
        display.ShowGoodByeMessage();
    }

}






