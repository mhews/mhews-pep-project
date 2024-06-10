package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    ObjectMapper om = new ObjectMapper();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", ctx -> {
            Account account = om.readValue(ctx.body(), Account.class);
            account = AccountService.registerUser(account);
            if (account == null){
                ctx.status(400);
            }
            else{
                ctx.json(account);
            }
        });

        app.post("/login", ctx -> {
            Account account = om.readValue(ctx.body(), Account.class);
            account = AccountService.loginUser(account);
            if (account == null){
                ctx.status(401);
            }
            else{
                ctx.json(account);
            }
        });

        app.post("/messages", ctx -> {
            Message message = om.readValue(ctx.body(), Message.class);
            message = MessageService.postMessage(message);
            if (message == null){
                ctx.status(400);
            }
            else{
                ctx.json(message);
            }
        });

        app.get("/messages", ctx -> {
            ctx.json(MessageService.getMessages());
        });

        app.get("/messages/{message_id}", ctx -> {
            Message message = MessageService.getMessageByID(ctx.pathParam("message_id"));
            if (message == null){
                ctx.result("");
            }
            else{
                ctx.json(message);
            }
        });

        app.delete("/messages/{message_id}", ctx -> {
            Message message = MessageService.deleteMessageByID(ctx.pathParam("message_id"));
            if (message == null){
                ctx.result("");
            }
            else{
                ctx.json(message);
            }
        });

        app.patch("/messages/{message_id}", ctx -> {
            Message message = om.readValue(ctx.body(), Message.class);
            message = MessageService.updateMessageByID(ctx.pathParam("message_id"), message.getMessage_text());
            if (message == null){
                ctx.status(400);
            }
            else{
                ctx.json(message);
            }
        });

        app.get("/accounts/{account_id}/messages", ctx -> {
                ctx.json(MessageService.getMessagesbyAccount(ctx.pathParam("account_id")));
        });

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }


}