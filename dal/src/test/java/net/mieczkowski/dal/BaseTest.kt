package net.mieczkowski.dal.discover

import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
open class BaseTest : KoinTest {
    @get:Rule
    val mockProvider = MockProviderRule.create { Mockito.mock(it.java) }
}