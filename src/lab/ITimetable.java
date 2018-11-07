package lab;

public interface ITimetable {

    /*************************************/
    /**
     * Informs whether a lesson can be transferred to the given term
     * @param term
     *         The term checked for the transferability
     * @param full_time
     *        Full-time or part-time studies
     * @return True if the lesson can be transferred to this term
     */
    boolean canBeTransferredTo(Term term, boolean full_time);

    /*************************************/
    /**
     * Returns True if the given term is busy
     * Should not be
     * confused with canBeTransferedTo()
     * since there might be free term
     * where the lesson
     * cannot be transfered
     * @param term
     *         Checked term
     * @return True if the term is busy
     */
    boolean busy(Term term);

    /*************************************/
    /**
     * Add the given lesson to the timetable
     *
     * @param lesson
    The added  lesson
     @return True if the lesson was added.  The lesson cannot be placed if the imetable slot is already occupied.
     */
    boolean put(Lesson lesson);

    /*************************************/
    /**
     * Transfer the lessons included in the timetable as described in the list of actions.
     * N-th action should be sent the n-th lesson in the timetable.
     */
    void perform(Action [] actions);

    /*************************************/
    /**
     * Get object (lesson) indicated by the given term
     * @param term
     *          The term of the lesson
     * @return Object or null if the term is free
     */
    Object get(Term term);
}