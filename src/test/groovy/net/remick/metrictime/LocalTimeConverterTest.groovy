package net.remick.metrictime

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

class LocalTimeConverterTest extends Specification {

  @Unroll
  def "ToMetricTime"() {
    expect:
    LocalTimeConverter.toMetricTime(lt) == mt

    where:
    lt                 || mt
    LocalTime.MIDNIGHT || 0.0d
    LocalTime.NOON     || 0.5d
    LocalTime.MAX      || 0.9999999999999885d
    LocalTime.of(7,
      34,
      32,
      890890)          || 0.315648158459375d
  }

  @Unroll
  def "ToLocalTime"() {
    expect:
    LocalTimeConverter.toLocalTime(mt).equals(lt)

    where:
    mt                 || lt
    0.0                || LocalTime.MIDNIGHT
    0.5                || LocalTime.NOON
    0.9999999999999885 || LocalTime.MAX
  }

}
