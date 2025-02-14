package strategy;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " via Credit Card");
        return true;
    }
}