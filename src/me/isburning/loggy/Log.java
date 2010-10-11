package me.isburning.loggy;

public class Log {
    private static LogLevel level;
    private static String tagFilter = null;

    /**
     * set the level at which we're logging, only this level or higher will be
     * logged
     * 
     * @param level
     */
    public static void setLogLevel(LogLevel level) {
        Log.level = level;
    }

    /**
     * sets a tag filter, only logs matching the tag will be printed
     * 
     * @param tag
     */
    public static void setTagFilter(String tag) {
        tagFilter = tag;
    }

    /**
     * resets the tag filter, all tags will be printed
     */
    public static void resetTagFilter() {
        tagFilter = null;
    }

    private static void log(String tag, String desc, LogLevel level) {
        if (Log.level.ordinal() <= level.ordinal() && (tagFilter == null || tag.equals(tagFilter))) {
            System.err.println(tag + ":" + desc);
        }
    }

    private static void log(String tag, String desc, LogLevel level, Throwable t) {
        assert level.ordinal() <= LogLevel.ERR.ordinal();
        System.err.println("-------- " + level.toString() + " --------");

        // do the log
        System.err.println();
        log(tag, desc, level);
        System.err.println();

        // print the trace
        System.err.println("-- begin trace --");
        System.err.println();
        t.printStackTrace(System.err);
        System.err.println();
        System.err.println("--- end trace ---");
        System.err.println("-------- " + level.toString() + " --------");
    }

    /**
     * write a spam log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void spam(String tag, String desc) {
        log(tag, desc, LogLevel.SPAM);
    }

    /**
     * write a debug log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void debug(String tag, String desc) {
        log(tag, desc, LogLevel.DEBUG);
    }

    /**
     * write an info log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void info(String tag, String desc) {
        log(tag, desc, LogLevel.INFO);
    }

    /**
     * write a wtf log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void wtf(String tag, String desc) {
        log(tag, desc, LogLevel.WTF);
    }

    /**
     * write a warning log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void warn(String tag, String desc) {
        log(tag, desc, LogLevel.WARN);
    }

    /**
     * write an error log
     * 
     * @param tag
     *            the tag to log with
     * @param desc
     *            some description
     */
    public static void err(String tag, String desc) {
        log(tag, desc, LogLevel.ERR);
    }

    /**
     * write an error log
     * @param tag the tag to log with
     * @param desc some description
     * @param t a throwable that caused the error
     */
    public static void err(String tag, String desc, Throwable t) {
        log(tag, desc, LogLevel.ERR, t);
    }

    /**
     * write a fatally bad log
     * @param tag the tag to log with
     * @param desc some description
     * @param t a Throwable that caused the fatality
     */
    public static void fatal(String tag, String desc, Throwable t) {
        log(tag, desc, LogLevel.FATAL, t);
    }

    public static void main(String[] args) {
        Log.setLogLevel(LogLevel.SPAM);
        Log.spam("testing", "testing");
        Log.wtf("wtf", "wtf");
        Log.err("error", "herp", new Exception("bees"));
        Log.setTagFilter("bees");
        Log.wtf("bees", "cheeses");
        Log.wtf("bees2", "cheeses");
        Log.resetTagFilter();
        Log.debug("ponies", "cows");
    }

}
