package StudyIoc;

public class HelloBean {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void showMessage() {
        System.out.println("Hello from HelloBean!");
        if (user != null) {
            System.out.println("User: " + user.getName() + ", Gender: " + user.getGender() + ",Object User HashCode：" + user.hashCode());
        }
        System.out.println("Object HashCode：" + this.hashCode());
    }
}
