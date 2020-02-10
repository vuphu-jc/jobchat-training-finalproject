import re

def isPhoneNumber(text):
    pattern = r'(?:\+ *)?\d[\d\- ]{7,}\d'
    return re.match(pattern, text)

def isEmail(text):
    pattern = r'(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)'
    return re.match(pattern, text)

def get_nullable_string(value: str):
    if len(value) > 0:
        return value
    return None

# print(isPhoneNumber("+0903524519"))
# print(isEmail("vtphuong214@gmail.com"))
