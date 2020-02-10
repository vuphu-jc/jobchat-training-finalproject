class User:
    def __init__(self):
        self.email = None
        self.uid = None
        self.display_name = None
        self.photo_url = None
        self.phone_number = None
        self.dob = None
        self.introduce = None

    @staticmethod
    def from_dict(source):
        user = User()

        def get_value(name: str):
            if name in source:
                return source[name]
            print(name)
            return None

        user.email = get_value('email')
        user.uid = get_value('uid')
        user.display_name = get_value('display_name')
        user.photo_url = get_value('photo_url')
        user.phone_number = get_value('phone_number')
        user.dob = get_value('dob')
        user.introduce = get_value('introduce')
        return user

    def to_dict(self):
        return {
            'email': self.email,
            'uid': self.uid,
            'display_name': self.display_name,
            'photo_url': self.photo_url,
            'phone_number': self.phone_number,
            'dob': self.dob,
            'introduce': self.introduce
        }