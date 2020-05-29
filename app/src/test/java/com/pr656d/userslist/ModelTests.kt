package com.pr656d.userslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.pr656d.userslist.model.Result
import com.pr656d.userslist.model.User
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class UserJsonTests {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun convertJsonToUser() {
        val jsonStr = "{\n" +
                "            \"gender\": \"female\",\n" +
                "            \"name\": {\n" +
                "                \"title\": \"Mrs\",\n" +
                "                \"first\": \"Pinja\",\n" +
                "                \"last\": \"Tuomala\"\n" +
                "            },\n" +
                "            \"location\": {\n" +
                "                \"street\": {\n" +
                "                    \"number\": 8791,\n" +
                "                    \"name\": \"Pyynikintie\"\n" +
                "                },\n" +
                "                \"city\": \"Virrat\",\n" +
                "                \"state\": \"Northern Ostrobothnia\",\n" +
                "                \"country\": \"Finland\",\n" +
                "                \"postcode\": 43883,\n" +
                "                \"coordinates\": {\n" +
                "                    \"latitude\": \"-8.9346\",\n" +
                "                    \"longitude\": \"48.0899\"\n" +
                "                },\n" +
                "                \"timezone\": {\n" +
                "                    \"offset\": \"+6:00\",\n" +
                "                    \"description\": \"Almaty, Dhaka, Colombo\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"email\": \"pinja.tuomala@example.com\",\n" +
                "            \"login\": {\n" +
                "                \"uuid\": \"ce8ce9f6-6a5e-49b3-8ccb-e319e27377b0\",\n" +
                "                \"username\": \"redtiger284\",\n" +
                "                \"password\": \"rhythm\",\n" +
                "                \"salt\": \"Nw5LTpaX\",\n" +
                "                \"md5\": \"5d46a2b0584322100e900f87b4168aaa\",\n" +
                "                \"sha1\": \"0dc008b509805ce95319b98d913897bcb4dadb2d\",\n" +
                "                \"sha256\": \"ab9ce43656d6f75a921114a1c64769028a600c7cff24326040a78af1d4214c3c\"\n" +
                "            },\n" +
                "            \"dob\": {\n" +
                "                \"date\": \"1950-08-01T07:49:33.573Z\",\n" +
                "                \"age\": 70\n" +
                "            },\n" +
                "            \"registered\": {\n" +
                "                \"date\": \"2012-05-27T08:25:51.353Z\",\n" +
                "                \"age\": 8\n" +
                "            },\n" +
                "            \"phone\": \"03-414-470\",\n" +
                "            \"cell\": \"042-440-83-97\",\n" +
                "            \"id\": {\n" +
                "                \"name\": \"HETU\",\n" +
                "                \"value\": \"NaNNA816undefined\"\n" +
                "            },\n" +
                "            \"picture\": {\n" +
                "                \"large\": \"https://randomuser.me/api/portraits/women/90.jpg\",\n" +
                "                \"medium\": \"https://randomuser.me/api/portraits/med/women/90.jpg\",\n" +
                "                \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/90.jpg\"\n" +
                "            },\n" +
                "            \"nat\": \"FI\"\n" +
                "        }"

        assertNotNull(Gson().fromJson(jsonStr, User::class.java))
    }

    @Test
    fun convertJsonToResult() {
        val resultStr = "{\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"gender\": \"female\",\n" +
                "            \"name\": {\n" +
                "                \"title\": \"Mrs\",\n" +
                "                \"first\": \"Pinja\",\n" +
                "                \"last\": \"Tuomala\"\n" +
                "            },\n" +
                "            \"location\": {\n" +
                "                \"street\": {\n" +
                "                    \"number\": 8791,\n" +
                "                    \"name\": \"Pyynikintie\"\n" +
                "                },\n" +
                "                \"city\": \"Virrat\",\n" +
                "                \"state\": \"Northern Ostrobothnia\",\n" +
                "                \"country\": \"Finland\",\n" +
                "                \"postcode\": 43883,\n" +
                "                \"coordinates\": {\n" +
                "                    \"latitude\": \"-8.9346\",\n" +
                "                    \"longitude\": \"48.0899\"\n" +
                "                },\n" +
                "                \"timezone\": {\n" +
                "                    \"offset\": \"+6:00\",\n" +
                "                    \"description\": \"Almaty, Dhaka, Colombo\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"email\": \"pinja.tuomala@example.com\",\n" +
                "            \"login\": {\n" +
                "                \"uuid\": \"ce8ce9f6-6a5e-49b3-8ccb-e319e27377b0\",\n" +
                "                \"username\": \"redtiger284\",\n" +
                "                \"password\": \"rhythm\",\n" +
                "                \"salt\": \"Nw5LTpaX\",\n" +
                "                \"md5\": \"5d46a2b0584322100e900f87b4168aaa\",\n" +
                "                \"sha1\": \"0dc008b509805ce95319b98d913897bcb4dadb2d\",\n" +
                "                \"sha256\": \"ab9ce43656d6f75a921114a1c64769028a600c7cff24326040a78af1d4214c3c\"\n" +
                "            },\n" +
                "            \"dob\": {\n" +
                "                \"date\": \"1950-08-01T07:49:33.573Z\",\n" +
                "                \"age\": 70\n" +
                "            },\n" +
                "            \"registered\": {\n" +
                "                \"date\": \"2012-05-27T08:25:51.353Z\",\n" +
                "                \"age\": 8\n" +
                "            },\n" +
                "            \"phone\": \"03-414-470\",\n" +
                "            \"cell\": \"042-440-83-97\",\n" +
                "            \"id\": {\n" +
                "                \"name\": \"HETU\",\n" +
                "                \"value\": \"NaNNA816undefined\"\n" +
                "            },\n" +
                "            \"picture\": {\n" +
                "                \"large\": \"https://randomuser.me/api/portraits/women/90.jpg\",\n" +
                "                \"medium\": \"https://randomuser.me/api/portraits/med/women/90.jpg\",\n" +
                "                \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/90.jpg\"\n" +
                "            },\n" +
                "            \"nat\": \"FI\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"info\": {\n" +
                "        \"seed\": \"b3560b88006ddd9e\",\n" +
                "        \"results\": 100,\n" +
                "        \"page\": 1,\n" +
                "        \"version\": \"1.3\"\n" +
                "    }\n" +
                "}"

        assertNotNull(Gson().fromJson(resultStr, Result::class.java))
    }
}