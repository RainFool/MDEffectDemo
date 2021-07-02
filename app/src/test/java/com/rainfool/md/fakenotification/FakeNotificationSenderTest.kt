package com.rainfool.md.fakenotification

import com.rainfool.md.common.RobolectricPowerMockCommon
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 *
 * @author krystian
 */
internal class FakeNotificationSenderTest : RobolectricPowerMockCommon() {

    @BeforeEach
    override fun setUp() {
        super.setUp()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testAdd() {
        MatcherAssert.assertThat(8, Matchers.equalTo(8))
    }
}