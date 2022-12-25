package app.response;

public class ResponseMessage {
    private static String message;

    private static ResponseMessage responseMessage;
    private ResponseMessage(String newMessage) {
        message = newMessage;
    }

    public static ResponseMessage getInstance(String newMessage){
        if(responseMessage == null){
            responseMessage = new ResponseMessage(newMessage);
        }
        setMessage(newMessage);
        return responseMessage;
    }

    private static void setMessage(String newMessage) {
        message = newMessage;
    }

    public String getMessage(){
        return message;
    }

}
