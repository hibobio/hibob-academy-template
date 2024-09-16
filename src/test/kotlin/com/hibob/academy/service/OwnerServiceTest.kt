package com.hibob.academy.service

import com.hibob.academy.dao.OwnerDao
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class OwnerServiceTest{
    private val ownerDaoMock = mock<OwnerDao>{}
    private val ownerService = OwnerService(ownerDaoMock)

    @Test
    fun `getAllOwner should throw exception if companyId less than zero `() {

    }
}