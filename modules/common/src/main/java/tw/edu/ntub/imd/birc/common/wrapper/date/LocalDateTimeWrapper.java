package tw.edu.ntub.imd.birc.common.wrapper.date;

import tw.edu.ntub.imd.birc.common.exception.NullParameterException;
import tw.edu.ntub.imd.birc.common.util.date.DatePatternBuilder;
import tw.edu.ntub.imd.birc.common.util.date.LocalDateTimeUtils;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class LocalDateTimeWrapper implements DateWrapper<LocalDateTime>, TimeWrapper<LocalDateTime> {
    private final LocalDateTime localDateTime;

    public LocalDateTimeWrapper() {
        this.localDateTime = LocalDateTimeUtils.now();
    }

    public LocalDateTimeWrapper(Date date) throws NullParameterException {
        if (date == null) {
            throw new NullParameterException();
        }
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        this.localDateTime = zonedDateTime.toLocalDateTime();
    }

    public LocalDateTimeWrapper(Calendar calendar) throws NullParameterException {
        if (calendar == null) {
            throw new NullParameterException();
        }
        Instant instant = calendar.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        this.localDateTime = zonedDateTime.toLocalDateTime();
    }

    public LocalDateTimeWrapper(LocalDateTime localDateTime) throws NullParameterException {
        if (localDateTime == null) {
            throw new NullParameterException();
        }
        this.localDateTime = localDateTime;
    }

    private LocalDateTimeWrapper newInstance(LocalDateTime localDateTime) {
        try {
            return new LocalDateTimeWrapper(localDateTime);
        } catch (NullParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getYear() {
        return localDateTime.getYear();
    }

    @Override
    public LocalDateTimeWrapper setYear(int amount) {
        return newInstance(localDateTime.withYear(amount));
    }

    @Override
    public LocalDateTimeWrapper addYear(int amount) {
        return newInstance(localDateTime.plusYears(amount));
    }

    @Override
    public int getMonth() {
        return localDateTime.getMonthValue();
    }

    @Override
    public LocalDateTimeWrapper setMonth(int amount) {
        return newInstance(localDateTime.withMonth(amount));
    }

    @Override
    public LocalDateTimeWrapper addMonth(int amount) {
        return newInstance(localDateTime.plusMonths(amount));
    }

    @Override
    public int getDay() {
        return localDateTime.getDayOfMonth();
    }

    @Override
    public LocalDateTimeWrapper setDay(int amount) {
        return newInstance(localDateTime.withDayOfMonth(amount));
    }

    @Override
    public LocalDateTimeWrapper addDay(int amount) {
        return newInstance(localDateTime.plusDays(amount));
    }

    @Override
    public long getFullMillisecond() {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return instant.toEpochMilli();
    }

    @Override
    public String format(DatePatternBuilder builder) {
        return LocalDateTimeUtils.format(localDateTime, builder);
    }

    @Override
    public int getHour() {
        return localDateTime.getHour();
    }

    @Override
    public LocalDateTimeWrapper setHour(int amount) {
        return newInstance(localDateTime.withHour(amount));
    }

    @Override
    public LocalDateTimeWrapper addHour(int amount) {
        return newInstance(localDateTime.plusHours(amount));
    }

    @Override
    public int getMinute() {
        return localDateTime.getMinute();
    }

    @Override
    public LocalDateTimeWrapper setMinute(int amount) {
        return newInstance(localDateTime.withMinute(amount));
    }

    @Override
    public LocalDateTimeWrapper addMinute(int amount) {
        return newInstance(localDateTime.plusMinutes(amount));
    }

    @Override
    public int getSecond() {
        return localDateTime.getSecond();
    }

    @Override
    public LocalDateTimeWrapper setSecond(int amount) {
        return newInstance(localDateTime.withSecond(amount));
    }

    @Override
    public LocalDateTimeWrapper addSecond(int amount) {
        return newInstance(localDateTime.plusSeconds(amount));
    }

    @Override
    public int getMillisecond() {
        return localDateTime.get(ChronoField.MILLI_OF_SECOND);
    }

    @Override
    public LocalDateTimeWrapper setMillisecond(int amount) {
        return newInstance(localDateTime.with(ChronoField.MILLI_OF_SECOND, amount));
    }

    @Override
    public LocalDateTimeWrapper addMillisecond(int amount) {
        return newInstance(localDateTime.plus(Duration.ofMillis(amount)));
    }

    @Override
    public LocalDateTime get() {
        return localDateTime;
    }

    @Override
    public Date toDate() {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    @Override
    public boolean isBefore(LocalDateTime localDateTime) {
        return this.localDateTime.isBefore(localDateTime);
    }

    @Override
    public boolean isAfter(LocalDateTime localDateTime) {
        return this.localDateTime.isAfter(localDateTime);
    }

    @Override
    public boolean isEqual(LocalDateTime localDateTime) {
        return this.localDateTime.isEqual(localDateTime);
    }
}
