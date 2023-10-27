package com.example.demo.service;

import com.example.demo.entity.Member;

public interface MemberService {
	// Memberテーブルを全検索するメソッドの呼び出しだけ抽象定義する
	public abstract Iterable<Member>  findAll();
	// Memberテーブルへinsertするメソッドの呼び出しだけ抽象定義する
	public abstract void insertMember( Member member );
}
