package strategy;

public class PayPalPayment implements PaymentStrategy{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " via PayPal");
        return true;
    }
}