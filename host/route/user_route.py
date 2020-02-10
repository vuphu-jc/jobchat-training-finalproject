import sys
sys.path.append('../')
from model.user.user_repository import UserRepository
from model.user.user import User
from flask import Blueprint, request, jsonify, abort
from flask.views import MethodView
import exceptions as exc
import constants

UserRepositoryImp = UserRepository()

class UserView(MethodView):
 
    def get(self, id=None, page=1):
        if not id:
            abort(405)
        else:
            user = UserRepositoryImp.read(id)
            if user == None:
                raise exc.InvalidUsage(constants.STR_USER_NOT_EXIST, 204)
            return jsonify(user.to_dict())
 
    def post(self):
        try:
            user = request.json['user']
            password = request.json['password']
            UserRepositoryImp.create(User.from_dict(user), password)
            return jsonify()
        except Exception as e:
            raise exc.InvalidUsage(str(e))
 
    def put(self):
        try:
            user = request.json
            UserRepositoryImp.update(User.from_dict(user))
            return jsonify()
        except Exception as e:
            raise exc.InvalidUsage(str(e))
        return
 
    def delete(self, id):
        return abort(405)


user_page = Blueprint('user', __name__)
user_view =  UserView.as_view('user_view')

user_page.add_url_rule(
    '/user', view_func=user_view, methods=['GET', 'POST']
)
user_page.add_url_rule(
    '/user/<string:id>', view_func=user_view, methods=['GET']
)
user_page.add_url_rule(
    '/user', view_func=user_view, methods=['PUT']
)