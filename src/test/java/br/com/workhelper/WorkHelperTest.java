package br.com.workhelper;

import com.intuit.karate.junit5.Karate;

class WorkHelperTest {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:br/com/workhelper/features").relativeTo(getClass());
    }
}
