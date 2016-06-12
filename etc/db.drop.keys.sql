SET search_path = chats, pg_catalog;
--
-- TOC entry 2148 (class 2606 OID 16558)
-- Name: fk_chats_mensajes_chid; Type: FK CONSTRAINT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes
    DROP CONSTRAINT fk_chats_mensajes_chid FOREIGN KEY (chid) REFERENCES chats(chid);


SET search_path = public, pg_catalog;

--
-- TOC entry 2149 (class 2606 OID 16563)
-- Name: fk_activaciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones
    DROP CONSTRAINT fk_activaciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2150 (class 2606 OID 16568)
-- Name: fk_avisos_stock_articulos; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY avisos_stock
    DROP CONSTRAINT fk_avisos_stock_articulos FOREIGN KEY (artid) REFERENCES articulos(artid);


--
-- TOC entry 2151 (class 2606 OID 16578)
-- Name: fk_bienvenida_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida
    DROP CONSTRAINT fk_bienvenida_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2152 (class 2606 OID 16583)
-- Name: fk_categorias_supercat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias
    DROP CONSTRAINT fk_categorias_supercat FOREIGN KEY (catid) REFERENCES categorias(catid) NOT VALID;


--
-- TOC entry 2153 (class 2606 OID 16588)
-- Name: fk_comentarios_artid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY comentarios
    DROP CONSTRAINT fk_comentarios_artid FOREIGN KEY (artid) REFERENCES articulos(artid);


--
-- TOC entry 2154 (class 2606 OID 16598)
-- Name: fk_direcciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones
    DROP CONSTRAINT fk_direcciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2157 (class 2606 OID 16603)
-- Name: fk_pedidos_detalles_artid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    DROP CONSTRAINT fk_pedidos_detalles_artid FOREIGN KEY (artid) REFERENCES articulos(artid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2158 (class 2606 OID 16608)
-- Name: fk_pedidos_detalles_pid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    DROP CONSTRAINT fk_pedidos_detalles_pid FOREIGN KEY (pid) REFERENCES pedidos(pid);


--
-- TOC entry 2155 (class 2606 OID 16613)
-- Name: fk_pedidos_direcciones; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos
    DROP CONSTRAINT fk_pedidos_direcciones FOREIGN KEY (did, uid) REFERENCES direcciones(did, uid);


--
-- TOC entry 2156 (class 2606 OID 16618)
-- Name: fk_pedidos_usuarios; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos
    DROP CONSTRAINT fk_pedidos_usuarios FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2159 (class 2606 OID 16623)
-- Name: fk_recordarme_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recordarme
    DROP CONSTRAINT fk_recordarme_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);
