public class APIErrorHandler {
    public String handleError(Exception e) {
        if (e instanceof RateLimitException) {
            return "Rate limit exceeded. Please wait 60 seconds.";
        } else if (e instanceof InvalidAPIKeyException) {
            return "Invalid API key. Please check configuration.";
        } else if (e instanceof NetworkException) {
            return "Network error. Please check your connection.";
        } else if (e instanceof TimeoutException) {
            return "Request timed out. Please try again.";
        } else {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}