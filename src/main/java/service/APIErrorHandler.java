package service;

/**
 * Creates a message for every potential API issue faced.
 */
public class APIErrorHandler {
    public static String handleError(Exception e) {
        String msg = e.getMessage();
        if(msg.contains("429")) return "Rate limit exceeded. Please wait.";
        if(msg.contains("401")) return "Invalid API key.";
        if(msg.contains("connect") || msg.contains("Network")) return "Network error.";
        if(msg.contains("timed out")) return "Request timed out.";
        if (msg.contains("Canceled")) {return "Generation canceled by user.";}
        return "Unexpected error: " + msg;
    }
}
