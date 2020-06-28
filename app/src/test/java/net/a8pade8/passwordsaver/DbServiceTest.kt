package net.a8pade8.passwordsaver

import android.content.Context
import android.os.Build.VERSION_CODES
import net.a8pade8.passwordsaver.data.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [VERSION_CODES.LOLLIPOP], packageName = "net.a8pade8.passwordsaver")
class DbServiceTests {

    lateinit var context: Context

    @Before
    fun createDb() {
        context = RuntimeEnvironment.application
        loading(context)
    }

    @Test
    @Throws(EmptyDataException::class)
    fun addRecordToPasswordsTest() {
        val id = addRecordToPasswords("vk.com", "unknown", "pass")
        Assert.assertTrue(isRecordExistInPasswords(id))
    }

    @Test
    fun deleteRecordFromPasswordsTest() {
        val id = addRecordToPasswords("vk.com", "unknown2", "pass")
        Assert.assertTrue(isRecordExistInPasswords(id))
        deleteRecordFromPasswords(id)
        Assert.assertFalse(isRecordExistInPasswords(id))
    }

    @Test
    fun isResourceExistTest() {
        addRecordToPasswords("vk.com", "unknown3", "pass")
        Assert.assertTrue(isContainResourceInPasswords("vk.com"))
    }

    @After
    fun deleteDb() {
        context.deleteDatabase(PSDBHelper.DATABASE_NAME)
    }
}