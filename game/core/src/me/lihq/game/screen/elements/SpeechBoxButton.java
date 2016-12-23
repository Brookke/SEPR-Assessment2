package me.lihq.game.screen.elements;

/**
 * Created by Jason on 23/12/2016.
 */
public class SpeechBoxButton {

    public String text;
    public EventHandler eventHandler;

    public SpeechBoxButton(String buttonText, EventHandler eventHandlerVal) {
        text = buttonText;
        eventHandler = eventHandlerVal;
    }

    public interface EventHandler {
        void handleClick(String buttonText);
    }
}