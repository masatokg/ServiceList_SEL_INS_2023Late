package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberCrudRepository;

@Service
public class MemberServiceImpl implements MemberService {

	// DB操作してくれるオブジェクトを変数として用意（リポジトリの注入）
	@Autowired
	MemberCrudRepository repository;
	@Override
	// Memberテーブルを全検索してそのリストを戻り値で返す
	public Iterable<Member> findAll() {
		// Springが用意したDB検索処理を使う
		Iterable<Member> members = repository.findAll();
		return members;
	}
	@Override
	public void insertMember(Member member) {
		// insertのSQLを実行する
		// リポジトリクラスのsaveメソッドを実行する
		repository.save(member);
	}



}
