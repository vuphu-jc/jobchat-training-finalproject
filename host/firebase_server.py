import firebase_admin
from firebase_admin import credentials, auth, firestore
import flask
from flask import request
from exceptions import InvalidUsage

cred = credentials.Certificate('./serviceAccountKey.json')
firebase_admin.initialize_app(cred)
app = flask.Flask(__name__)
database = firestore.client()

@app.errorhandler(InvalidUsage)
def handle_invalid_usage(error):
    response = flask.jsonify(error.to_dict())
    response.status_code = error.status_code
    return response

from route import user_route
app.register_blueprint(user_route.user_page)

if __name__ == "__main__":
    app.run(host='0.0.0.0',debug=True)