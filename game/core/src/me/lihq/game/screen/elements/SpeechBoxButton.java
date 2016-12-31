package me.lihq.game.screen.elements;

/**
 * Created by Jason on 23/12/2016.
 */
public class SpeechBoxButton {

    public String text;
    public int result;
    public EventHandler eventHandler;

    public SpeechBoxButton(String buttonText,int buttonResult, EventHandler eventHandlerVal) {
        text = buttonText;
        result = buttonResult;
        eventHandler = eventHandlerVal;
    }

    public interface EventHandler {
        void handleClick(int result);
    }
}