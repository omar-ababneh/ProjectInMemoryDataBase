package ServerSide;

public class Server {
    private static ServerCommunicate serverCommunicate=new ServerCommunicate();
    public static void main(String[] args) {
        serverCommunicate.init();
        while (true){
            serverCommunicate.AcceptConnection();
            new Thread(new HandleAClient()).start();
        }
    }
    private static class HandleAClient implements Runnable {
        private  Display display=new Display();
        @Override
        public void run() {
            display.ShowHelloMessage();
            SelectOperation();
        }
        public  void SelectOperation() {
            Boolean ClientIsActive=true;
            while (ClientIsActive){
                String Operation=serverCommunicate.OperationType();
                    switch (Operation) {
                        case "Log in":
                            serverCommunicate.CommunicateInOrderLogin();
                            break;
                        case "Delete":
                            serverCommunicate.CommunicateInOrderDelete();
                            break;
                        case "Insert":
                            serverCommunicate.CommunicateInOrderInsert();
                            break;
                        case "Update":
                            serverCommunicate.CommunicateInOrderUpdate();
                            break;
                        case "Search":
                            serverCommunicate.CommunicateInOrderSearch();
                            break;
                        case "Log Out":
                            serverCommunicate.CommunicateInOrderLogout();
                            display.ShowGoodByeMessage();
                            ClientIsActive=false;
                            break;
                        case "CreateNewUser":
                            serverCommunicate.CommunicateInOrderCreateUser();
                            break;
                        default: display.ShowSorryMessage(); ClientIsActive=false; serverCommunicate.close();
                            break;
                }


            }

        }
    }
}


