package me.lullaby.tradlinxbackend.application.authenticate

import me.lullaby.tradlinxbackend.common.HttpException
import org.springframework.http.HttpStatus

class DuplicatedAccountException: HttpException(httpStatus = HttpStatus.CONFLICT, message = "이미 존재하는 사용자 입니다.")
// class UserNotFoundException: HttpException(httpStatus = HttpStatus.NOT_FOUND, message = "사용자를 찾을 수 없습니다.")

class AuthenticateFailException: HttpException(httpStatus = HttpStatus.BAD_REQUEST, "아이디 또는 패스워드가 일치하지 않습니다.")