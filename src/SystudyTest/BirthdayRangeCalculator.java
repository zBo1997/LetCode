package SystudyTest;

import java.time.LocalDate;

public class BirthdayRangeCalculator {

  // 定义一个方法来计算生日的日期范围（精确到月日）
  public static BirthdayRange calculateBirthdayRange(int ageMin, int ageMax) {
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    int currentMonth = currentDate.getMonthValue();
    int currentDay = currentDate.getDayOfMonth();

    int upperLimitYear = currentYear - ageMin;
    int lowerLimitYear = currentYear - ageMax - 1;

    // 上限日期为当年的生日
    LocalDate upperDate = LocalDate.of(upperLimitYear, currentMonth, currentDay);
    // 下限日期为去年的生日
    LocalDate lowerDate = LocalDate.of(lowerLimitYear, currentMonth, currentDay);

    return new BirthdayRange(upperDate, lowerDate);
  }

  public static void main(String[] args) {
    BirthdayRange birthdayRange = calculateBirthdayRange(30, 20);
    System.out.println("上限日期：" + birthdayRange.getUpperDate());
    System.out.println("下限日期：" + birthdayRange.getLowerDate());
  }
}

class BirthdayRange {
  private LocalDate upperDate;
  private LocalDate lowerDate;

  public BirthdayRange(LocalDate upperDate, LocalDate lowerDate) {
    this.upperDate = upperDate;
    this.lowerDate = lowerDate;
  }

  public LocalDate getUpperDate() {
    return upperDate;
  }

  public LocalDate getLowerDate() {
    return lowerDate;
  }
}