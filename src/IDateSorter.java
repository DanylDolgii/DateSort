import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 *
 *
 * The Date sorter interface, implement the single method
 * within this interface.
 */
public interface IDateSorter {
    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2005-07-01, 2005-01-02, 2005-01-01, 2005-05-03)
     * would sort to
     * (2005-01-01, 2005-01-02, 2005-07-01, 2005-05-03)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    static Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {

        return unsortedDates.stream()
                .sorted(BY_MONTH_R_ASCENDING_NO_R_DESCENDING)
                .toList();
    }

    Predicate<LocalDate> NO_R = ld -> !ld.getMonth().name().contains("R");

    Comparator<LocalDate> BY_MONTH_R_ASCENDING_NO_R_DESCENDING =
            Comparator.comparing(NO_R::test)
                    .thenComparingLong(ld -> NO_R.test(ld) ?
                            (-1) * ld.toEpochDay() : ld.toEpochDay()
                    );

    static void main(String[] args) {
        List<LocalDate> dates = List.of(
                LocalDate.parse("2005-07-01"), LocalDate.parse("2005-01-02"),
                LocalDate.parse("2005-01-01"), LocalDate.parse("2005-05-03")
        );

        sortDates(dates).forEach(System.out::println);
    }
}