package org.danit.family;

import org.danit.family.human.Man;
import org.danit.family.human.Woman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyDaoTest {
    Woman woman = new Woman();
    Man man = new Man();
    Family family = new Family(man, woman);
    FamilyDao familyDao = new FamilyDao() {{
        save(family);
    }};

    @Test
    void get() {
        assertEquals(1, familyDao.getAll().size());
        assertEquals(family, familyDao.get(0).orElse(null));
    }

    @Test
    void saveAndDeleteByObject() {
        Man newMan = new Man();
        Woman newWoman = new Woman();
        Family newFamily = new Family(newMan, newWoman);

        familyDao.save(newFamily);

        assertEquals(2, familyDao.getAll().size());
        assertEquals(newFamily, familyDao.get(1).orElse(null));

        familyDao.delete(newFamily);
        assertEquals(1, familyDao.getAll().size());
    }

    @Test
    void getAll() {
        for (int i = 0; i < 10; i++) {
            Man newMan = new Man();
            Woman newWoman = new Woman();
            Family newFamily = new Family(newMan, newWoman);

            familyDao.save(newFamily);

            assertEquals(i + 2, familyDao.getAll().size());
            assertTrue(familyDao.getAll().contains(family));
        }
    }
    @Test

    void saveAndDeleteById() {
        Man newMan = new Man();
        Woman newWoman = new Woman();
        Family newFamily = new Family(newMan, newWoman);

        familyDao.save(newFamily);
        assertEquals(2, familyDao.getAll().size());

        boolean deleteStatus1 = familyDao.delete(1);
        assertTrue(deleteStatus1);
        assertEquals(1, familyDao.getAll().size());

        boolean deleteStatus2 = familyDao.delete(1);
        assertFalse(deleteStatus2);
    }
}