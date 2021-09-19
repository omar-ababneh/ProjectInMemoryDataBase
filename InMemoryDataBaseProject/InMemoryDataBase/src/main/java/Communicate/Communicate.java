package Communicate;


 public interface Communicate {
   void init();
   Boolean CommunicateInOrderLogin();
   void CommunicateInOrderInsert();
   void CommunicateInOrderDelete();
   void CommunicateInOrderUpdate();
   void CommunicateInOrderSearch();
   Boolean CommunicateInOrderCreateUser();
   void CommunicateInOrderLogout();
   void close();
}
