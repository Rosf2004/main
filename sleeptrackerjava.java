import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SleepTrackerJava {
    private int trackedDays = 0;
    private boolean trackProgress = false;

    public void calculateSleep(int hours) {
        int requiredSleep = (hours < 18) ? 9 : 7;
        int difference = requiredSleep - hours;
        String status = (difference < 0) ? "You are oversleeping!"
                      : (difference == 0) ? "You are getting enough sleep."
                      : "You need more sleep.";

        JOptionPane.showMessageDialog(null, "You need a total of " + requiredSleep + " hours of sleep.\n"
                                             + "You are getting " + hours + " hours of sleep daily.\n"
                                             + status);
    }

    public void trackProgressOption() {
        int choice = JOptionPane.showConfirmDialog(null, "Would you like to track your sleep progress for the next 7 days?",
                                                    "Track Progress", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            trackProgress = true;
            setTimer();
        }
    }

    public void setTimer() {
        new Thread(() -> {
            try {
                for (int i = 1; i <= 7; i++) {
                    Thread.sleep(5000);
                    trackedDays++;
                    if (trackedDays < 7) {
                        trackProgressOption();
                    } else {
                        displayProgress();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void displayProgress() {
        StringBuilder progress = new StringBuilder("Here's your progress for the week:\n");
        for (int i = 1; i <= 7; i++) {
            progress.append("Day ").append(i).append(": Sleep status\n");
        }
        JOptionPane.showMessageDialog(null, progress.toString());
    }

    public void startProgram(int hours) {
        if (hours < 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }
        calculateSleep(hours);
        trackProgressOption();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            JOptionPane.showMessageDialog(null, "Usage: java SleepTrackerJava <hours>");
            return;
        }
        try {
            int hours = Integer.parseInt(args[0]);
            SleepTrackerJava tracker = new SleepTrackerJava();
            tracker.startProgram(hours);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
    }
}
