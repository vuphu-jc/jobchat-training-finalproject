from firebase_admin import auth, firestore
import constants
import utils
from .user import User

class UserRepository:
    def __init__(self):
        self.database = firestore.client()
    
    def __get_uid_by_username(self, username: str):
        uid = None
        if utils.isEmail(username):
            uid = auth.get_user_by_email(username).uid
        elif (utils.isPhoneNumber(username)):
            uid = auth.get_user_by_phone_number(username).uid
        return uid

    def __get_collection(self):
        return self.database.collection(constants.USER_COLLECTION)

    def create(self, user: User, password: str):
        user_record = auth.create_user(
            email = user.email,
            display_name = user.display_name,
            phone_number = utils.get_nullable_string(user.phone_number),
            password = password,
            disabled = False,
        )
        user.uid = user_record.uid
        self.__get_collection().document(user.uid).set(user.to_dict())

    def read(self, username: str):
        try:
            uid = self.__get_uid_by_username(username)
            dict = self.__get_collection().document(uid).get().to_dict()
            return User.from_dict(dict)
        except:
            return None

    def update(self, user: User):
        

        auth.update_user(
            user.uid,
            email = user.email,
            display_name = user.display_name,
            phone_number = utils.get_nullable_string(user.phone_number),
            disabled = False,
        )
        if self.__get_collection().document(user.uid).get().exists:
            return self.__get_collection().document(user.uid).set(user.to_dict())

    def delete(self, username: str):
        uid = self.__get_uid_by_username(username)
        auth.delete_user(uid)
        self.database.collection(constants.USER_COLLECTION).document(uid).delete()
