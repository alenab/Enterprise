package ua.goit.calculate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.calculator.Calculator;

public class Application {

    private Calculator calculator;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "aop-context.xml");
        Application application = applicationContext.getBean("application", Application.class);
        application.start();
    }

    public void start() {
        System.out.println("RESULT = " + calculator.calculate(InputData.readInputString()));
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
}
