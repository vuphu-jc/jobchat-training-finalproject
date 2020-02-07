import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth
from firebase_admin import firestore
import flask
from flask import request
import utils

cred = credentials.Certificate('./serviceAccountKey.json')
firebase_admin.initialize_app(cred)
app = flask.Flask(__name__)
db = firestore.client()

@app.route('/register', methods=['POST'])
def register():
    def emptyString(text: str):
        if len(text) == 0:
            return None
        return text

    email_address = request.values.get('email')
    display_name = request.values.get('displayName')
    phone_number = request.values.get('phoneNumber')
    password = request.values.get('password')
    auth.create_user(
        email = emptyString(email_address),
        display_name = emptyString(display_name),
        phone_number = emptyString(phone_number),
        password = emptyString(password),
        disabled = False,
    )
    return flask.jsonify(True)

@app.route('/user-information', methods=['GET'])
def user_information():
    email_address = request.args.get('username')
    return flask.jsonify(get_users(email_address))

def get_users(username):
    try:
        user = None
        if utils.isEmail(username):
            user = auth.get_user_by_email(username)
        elif (utils.isPhoneNumber(username)):
            user = auth.get_user_by_phone_number(username)
        return {
            "email": user.email,
            "displayName": user.display_name,
            "uid": user.uid,
            "photoUrl": user.photo_url if user.photo_url != None else "",
            "phoneNumber": user.phone_number
            }
    except:
        return None

if __name__ == "__main__":
    app.run(host='0.0.0.0',debug=True)
