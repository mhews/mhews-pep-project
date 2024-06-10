package Service;

import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public static Message postMessage(Message message){
        if (AccountDAO.lookupID(message.getPosted_by()) == null){
            return null;
        }
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() >= 255){
            return null;
        }
        return (MessageDAO.insertMessage(message));
    }
    public static ArrayList<Message> getMessages(){
        return (MessageDAO.getAllMessages());
    }
    public static Message getMessageByID(String id){
        return (MessageDAO.lookupMessageID(id));
    }
    public static Message deleteMessageByID(String id){
        Message message = MessageDAO.lookupMessageID(id);
        if (MessageDAO.deleteMessageID(id) > 0){
            return message;
        }
        return null;
    }
    public static Message updateMessageByID(String id, String text){
        if (text.length() == 0 || text.length() >= 255){
            return null;
        }
        if (MessageDAO.updateMessageID(id, text) > 0){
            return MessageDAO.lookupMessageID(id);
        }
        return null;
    }
    public static ArrayList<Message> getMessagesbyAccount(String id){
        return (MessageDAO.getAccountMessages(id));
    }
}
