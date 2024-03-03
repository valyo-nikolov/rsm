package rsm.first_task;

import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

public class FooBarTests {

    @Test
    @DisplayName("Check fooBar() with valid not repeated numbers as input")
    public void fooBar1() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "1,2,3,4,5,6,45";
        String output = "1,2,foo,4,bar,foo,foobar";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getStandardOutput().get(0));
    }

    @Test
    @DisplayName("Check fooBar() with valid numbers as input, some of them repeated")
    public void fooBar2() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "1,2,3,1,2,3,1,1";
        String output = "1,2,foo,1-copy,2-copy,foo-copy,1-copy,1-copy";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getStandardOutput().get(0));
    }

    @Test
    @DisplayName("Check fooBar() with valid numbers as input, all of them repeated")
    public void fooBar3() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "1,1,3,3,5,5,45,45";
        String output = "1,1-copy,foo,foo-copy,bar,bar-copy,foobar,foobar-copy";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getStandardOutput().get(0));
    }

    @Test
    @DisplayName("Check fooBar() with valid negative numbers as input")
    public void fooBar4() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "-1,1,-3,-3,-5,-5,-45,-45";
        String output = "-1,1,foo,foo-copy,bar,bar-copy,foobar,foobar-copy";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getStandardOutput().get(0));
    }

    @Test
    @DisplayName("Check fooBar() with zero in numbers as input")
    public void fooBar5() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "0,0,0";
        String output = "foobar,foobar-copy,foobar-copy";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getStandardOutput().get(0));
    }

    @Test
    @DisplayName("Check fooBar() with invalid number as input")
    public void fooBar6() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        FooBar fooBar = new FooBar();
        String input = "1,1,15,x,17";
        String output = "Invalid integer input";
        fooBar.fooBar(input);
        Assertions.assertEquals(output, consoleCaptor.getErrorOutput().get(0));
    }
}
