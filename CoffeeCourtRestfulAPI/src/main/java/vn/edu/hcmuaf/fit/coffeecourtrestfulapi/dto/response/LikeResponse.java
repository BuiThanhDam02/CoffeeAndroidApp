package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response;

public class LikeResponse {
    private String message;
    private boolean success;

    public LikeResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public LikeResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LikeResponse{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
