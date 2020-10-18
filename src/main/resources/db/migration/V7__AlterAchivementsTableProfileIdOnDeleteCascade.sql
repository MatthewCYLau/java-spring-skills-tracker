ALTER TABLE achievements
   DROP CONSTRAINT achievements_profile_id_fkey
 , ADD  CONSTRAINT achievements_profile_id_fkey
   FOREIGN KEY (profile_id) REFERENCES profiles (profile_id) ON DELETE CASCADE;