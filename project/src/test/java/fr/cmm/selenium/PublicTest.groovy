package fr.cmm.selenium

import org.fluentlenium.adapter.FluentTest
import org.junit.Test

class PublicTest extends FluentTest {
    @Test
    void 'visit home page'() {
        goTo 'http://localhost:8080/'

        assert $('h1').text == 'Des recettes, des idées pour déguster'
    }

    @Test
    void 'test number of home recipes'() {
        goTo 'http://localhost:8080/'
        assert $('.thumbnail').size() == 30
    }
}