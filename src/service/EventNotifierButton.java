package service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EventNotifierButton extends JButton {
    private List<ActionListenerWithPriority> listeners;

    public EventNotifierButton(String text) {
        super(text);
        listeners = new ArrayList<>();

        // Add action listener to handle button clicks
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify listeners when the button is clicked
                notifyListeners();
            }
        });
    }

    // Method to add listeners with specified priorities
    public void addCustomActionListener(ActionListener listener, int priority) {
        listeners.add(new ActionListenerWithPriority(listener, priority));
    }

    // Method to remove listeners
    public void removeActionListener(ActionListener listener) {
        listeners.removeIf(listenerWithPriority -> listenerWithPriority.getActionListener().equals(listener));
    }

    // Method to notify listeners in order of priority
    private void notifyListeners() {
        // Sort the listeners by priority
        listeners.sort((l1, l2) -> Integer.compare(l1.getPriority(), l2.getPriority()));

        // Notify listeners in order of priority
        for (ActionListenerWithPriority listener : listeners) {
            listener.getActionListener().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getText()));
        }
    }

    // Inner class to represent ActionListener with priority
    private class ActionListenerWithPriority {
        private ActionListener actionListener;
        private int priority;

        public ActionListenerWithPriority(ActionListener actionListener, int priority) {
            this.actionListener = actionListener;
            this.priority = priority;
        }

        public ActionListener getActionListener() {
            return actionListener;
        }

        public int getPriority() {
            return priority;
        }
    }
}
