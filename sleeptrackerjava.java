import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sleeptrackerjava
    {
        private int totalHours = 0;
        //I will integrate these variables into the html file
        private int trackedDays = 0;
        private boolean trackProgress = false;
        //I will integrate these variables into the html file

        public void calculateSleep(int hours)
        {
            int requiredSleep = 7;
            if (hours < 18)
            {
                requiredSleep = 9;
            }
        

        int difference = requiredSleep - hours;
        String status;

        if (difference < 0)
        {
            status = "You are oversleeping!";
        }
        else if (difference == 0)
        {
            status = "You are getting enough sleep.";
        }
        else
        {
            status = "You need more sleep.";
        }

        System.out.println("You need a total of " + requiredSleep + " hours of sleep.");
        System.out.println("You are getting " + hours + " hours of sleep daily.");
        System.out.println(status);

    }

    public void trackProgressOption()
    {
        System.out.println("Would you like to track you sleep progress for the next 7 days?");
        Button yesButton = new Button("Yes");
        yesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                trackProgress = true;
                setTimer();
            }
        });
    }

    public void setTimer()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                    trackedDays++;
                    if (trackedDays < 7)
                    {
                        trackProgressOption();
                    }
                    else
                    {
                        displayProgress();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void displayProgress()
    {
        System.out.println("Here's your progress fore the week:");
        for (int i = 1; i <= 7; i++)
        {
            System.out.println("Day " + i + ": Sleep status");
        }
    }

    public void startProgram(int hours)
    {
        if (hours < 0)
        {
            System.out.println("Please enter a valid number.");
        }

        calculateSleep(hours);
        trackProgressOption();
    }

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("Usage: java sleeptrackerjava <hours>");
        }

        int hours = Integer.parseInt(args[0]);
        sleeptrackerjava tracker = new sleeptrackerjava();
        tracker.startProgram(hours);
    }
}